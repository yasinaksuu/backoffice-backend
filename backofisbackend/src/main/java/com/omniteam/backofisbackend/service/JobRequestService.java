package com.omniteam.backofisbackend.service;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.jobrequest.JobRequestAddDto;
import com.omniteam.backofisbackend.dto.jobrequest.JobRequestUpdateDto;
import com.omniteam.backofisbackend.entity.JobRequest;
import com.omniteam.backofisbackend.entity.RequestStatus;
import com.omniteam.backofisbackend.shared.result.DataResult;
import com.omniteam.backofisbackend.shared.result.Result;
import org.springframework.data.domain.Pageable;

public interface JobRequestService {
    DataResult<PagedDataWrapper<JobRequest>> getAll(Pageable pageable);

    DataResult<JobRequest> getById();

    Result add(JobRequestAddDto jobRequestAdd);

    Result update(JobRequestUpdateDto jobRequestUpdate);

    Result setStatus(Integer JobRequestId, RequestStatus requestStatus);


}
