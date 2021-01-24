package com.test.sangcheol.service;

import com.test.sangcheol.domain.FileType;
import com.test.sangcheol.domain.FileTypeType;
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

    public FileType create(FileType obj) {
        return fileTypeRepository.save((FileType)obj);
    }

    public List<FileType> getAllCustomTypeList() {
        return fileTypeRepository.findAllByTypeIsAndExpiredAtIsNull(FileTypeType.CUSTOM);
    }

    public List<FileType> getAllFixedTypeList() {
        return fileTypeRepository.findAllByTypeIsAndExpiredAtIsNull(FileTypeType.FIXED);
    }

    @Transactional
    public FileType deleteType(FileTypeType type, String fileType) {
        FileType targetType = fileTypeRepository.findByTypeAndFileTypeAndExpiredAtIsNull(type, fileType);
        targetType.setExpiredAt(LocalDateTime.now());
        return targetType;
    }

    public FileType findFileType(FileTypeType type, String fileType) {
        return fileTypeRepository.findByTypeAndFileType(type, fileType);
    }

    @Transactional
    public void reviveFileType(FileType fileType) {
        fileType.setExpiredAt(null);
    }
}
