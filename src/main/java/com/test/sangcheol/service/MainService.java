package com.test.sangcheol.service;

import com.test.sangcheol.domain.RejectedFile;
import com.test.sangcheol.domain.RejectedFileType;
import com.test.sangcheol.repository.FileTypeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MainService {

    private final FileTypeRepository fileTypeRepository;

    public MainService(FileTypeRepository fileTypeRepository) {
        this.fileTypeRepository = fileTypeRepository;
    }

    public RejectedFile create(RejectedFile rejectedFile) {
        return fileTypeRepository.save((RejectedFile)rejectedFile);
    }

    public List<RejectedFile> getAllCustomTypeList() {
        return fileTypeRepository.findAllByFileTypeIsAndExpiredAtIsNull(RejectedFileType.CUSTOM);
    }

    public List<RejectedFile> getAllFixedTypeList() {
        return fileTypeRepository.findAllByFileTypeIsAndExpiredAtIsNull(RejectedFileType.FIXED);
    }

    @Transactional
    public RejectedFile deleteType(RejectedFileType type, String fileType) {
        RejectedFile targetType = fileTypeRepository.findByFileTypeAndFileTypeStringAndExpiredAtIsNull(type, fileType);
        targetType.setExpiredAt(LocalDateTime.now());
        return targetType;
    }

    public RejectedFile findFileType(RejectedFileType type, String fileType) {
        return fileTypeRepository.findByFileTypeAndFileTypeString(type, fileType);
    }

    @Transactional
    public void reviveFileType(RejectedFile rejectedFile) {
        rejectedFile.setExpiredAt(null);
    }
}
