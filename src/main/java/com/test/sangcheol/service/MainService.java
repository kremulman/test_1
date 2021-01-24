package com.test.sangcheol.service;

import com.test.sangcheol.domain.FileType;
import com.test.sangcheol.repository.FileTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    private final FileTypeRepository fileTypeRepository;

    public MainService(FileTypeRepository fileTypeRepository) {
        this.fileTypeRepository = fileTypeRepository;
    }

    public FileType create(Object obj) {
        return fileTypeRepository.save((FileType)obj);
    }

    public List<FileType> read() {
        return fileTypeRepository.findAll();
    }

}
