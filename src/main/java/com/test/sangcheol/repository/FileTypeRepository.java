package com.test.sangcheol.repository;

import com.test.sangcheol.domain.FileType;
import com.test.sangcheol.domain.FileTypeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileTypeRepository extends JpaRepository<FileType, Long> {
    List<FileType> findAllByTypeIsAndExpiredAtIsNull(FileTypeType type);

    FileType findByTypeAndFileTypeAndExpiredAtIsNull(FileTypeType fixed, String fileType);

    FileType findByTypeAndFileType(FileTypeType type, String fileType);
}
