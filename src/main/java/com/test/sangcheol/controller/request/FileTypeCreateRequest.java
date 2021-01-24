package com.test.sangcheol.controller.request;

import com.test.sangcheol.domain.RejectedFileType;
import lombok.Data;

@Data
public class FileTypeCreateRequest {

    private String fileTypeString;
    private RejectedFileType fileType = RejectedFileType.CUSTOM;
    private boolean isDeleted;

}
