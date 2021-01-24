package com.test.sangcheol.controller;

import com.google.common.collect.Lists;
import com.test.sangcheol.controller.request.FileTypeCreateRequest;
import com.test.sangcheol.domain.RejectedFile;
import com.test.sangcheol.domain.RejectedFileType;
import com.test.sangcheol.service.MainService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/main")
public class MainController {

    /**
     * 1-1. 고정 확장자는 차단을 자주하는 확장자를 리스트이며, default는 unCheck되어져 있습니다.
     * 1-2. 고정 확장자를 check or uncheck를 할 경우 db에 저장됩니다. - 새로고침시 유지되어야합니다.
     * (아래쪽 커스텀 확장자에는 표현되지 않으니 유의해주세요.)
     * 2-1. 확장자 최대 입력 길이는 20자리
     * 2-2. 추가버튼 클릭시 db 저장되며, 아래쪽 영역에 표현됩니다.
     * 3-1. 커스텀 확장자는 최대 200개까지 추가가 가능
     * 3-2. 확장자 옆 X를 클릭시 db에서 삭제
     * 위 요건 이외에 어떤 점을 고려했는지 적어주세요.
     * ex) 커스텀 확장자 중복 체크
     * 커스텀 확장자 sh를 추가한 후 다시 sh를 추가했을 때 고려하여 개발
     */
    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping("/gettype")
    public List<RejectedFile> getAllAllowedType() {
        List<RejectedFile> rejectedFileList = mainService.getAllCustomTypeList();
        return rejectedFileList;
    }

    // 저장 및 업데이트는 1개 요소씩
    @PostMapping("/createtype")
    public RejectedFile createAllowedType(@RequestBody FileTypeCreateRequest type) {
        validateType(type); // 입력값 검증
        if(type.getFileType().equals(RejectedFileType.CUSTOM)) {
            validateCount(type); // 갯수 검증
        }
        RejectedFile rejectedFile = mainService.findFileType(type.getFileType(), type.getFileTypeString());
        if (Objects.isNull(rejectedFile)) { // 없으면 신규생성
            rejectedFile = mainService.create(RejectedFile.builder().fileType(type.getFileType()).fileTypeString(type.getFileTypeString()).build()); // 저장
        } else if(rejectedFile.getExpiredAt() == null) { // 중복입력
            throw new RuntimeException("이미 입력된 확장자 입니다.");
        } else if(rejectedFile.getExpiredAt() != null) { // 기존에 입력된 데이터이나 삭제 됐을 경우
            mainService.reviveFileType(rejectedFile);
        }
        return rejectedFile; // 결과 리턴
    }

    // 저장 및 업데이트는 1개 요소씩, 삭제의 경우는 조회 후 소프트딜리트로 처리
    @DeleteMapping("/deletetype")
    public RejectedFile deleteAllowedType(@RequestBody FileTypeCreateRequest type) {
        validateType(type); // 입력값 검증
        RejectedFile rejectedFile = mainService.deleteType(type.getFileType(), type.getFileTypeString()); // 저장
        return rejectedFile; // 결과 리턴
    }

    private void validateCount(FileTypeCreateRequest type) {
        List<RejectedFile> rejectedFileList = mainService.getAllCustomTypeList();
        List<String> strList = rejectedFileList.stream().map(e -> e.getFileTypeString().trim()).distinct().collect(Collectors.toList());
        if (!strList.contains(type.getFileTypeString()) && strList.size() >= 200) {
            throw new RuntimeException("커스텀 확장자는 200개 이하여야 합니다");
        }
    }

    private void validateType(FileTypeCreateRequest type) {
        // 조건
        if(StringUtils.isEmpty(type.getFileTypeString()) || type.getFileTypeString().trim().length() > 20) {
            throw new RuntimeException("확장자는 20자 이하여야 합니다");
        }

        // 자주쓰는 확장자의 경우는 FIXED 타입을 설정
        List<String> fixedList = Lists.newArrayList("bat","cmd","com","cpl","exe","scr","js");
        if(fixedList.contains(type.getFileTypeString().trim())) {
            type.setFileType(RejectedFileType.FIXED);
        }
    }

}
