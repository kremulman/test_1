package com.test.sangcheol.repository;

import com.test.sangcheol.domain.RejectedFile;
import com.test.sangcheol.domain.RejectedFileType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileTypeRepository extends JpaRepository<RejectedFile, Long> {
    List<RejectedFile> findAllByFileTypeIsAndExpiredAtIsNull(RejectedFileType type);

    RejectedFile findByFileTypeAndFileTypeStringAndExpiredAtIsNull(RejectedFileType fixed, String fileType);

    RejectedFile findByFileTypeAndFileTypeString(RejectedFileType type, String fileType);
}
