package com.omniteam.backofisbackend.service.implementation;

import com.omniteam.backofisbackend.dto.PagedDataWrapper;
import com.omniteam.backofisbackend.dto.jobrequest.JobRequestAddDto;
import com.omniteam.backofisbackend.dto.jobrequest.JobRequestUpdateDto;
import com.omniteam.backofisbackend.entity.JobRequest;
import com.omniteam.backofisbackend.entity.RequestStatus;
import com.omniteam.backofisbackend.repository.JobRequestRepository;
import com.omniteam.backofisbackend.service.JobRequestService;
import com.omniteam.backofisbackend.shared.constant.ResultMessage;
import com.omniteam.backofisbackend.shared.mapper.JobRequestMapper;
import com.omniteam.backofisbackend.shared.result.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class JobRequestServiceImpl implements JobRequestService {
    JobRequestRepository jobRequestRepository;
    JobRequestMapper jobRequestMapper;

    @Autowired
    public JobRequestServiceImpl(JobRequestRepository jobRequestRepository, JobRequestMapper requestMapper) {
        this.jobRequestRepository = jobRequestRepository;
        this.jobRequestMapper = requestMapper;
    }

    @Override
    public DataResult<PagedDataWrapper<JobRequest>> getAll(Pageable pageable) {
        Page<JobRequest> jobRequestPage = this.jobRequestRepository.getAllBy(pageable);
        PagedDataWrapper dataWrapper = new PagedDataWrapper(jobRequestPage);
        SuccessDataResult<PagedDataWrapper<JobRequest>> result = new SuccessDataResult<PagedDataWrapper<JobRequest>>(dataWrapper);

        return result;
    }

    @Override
    public DataResult<JobRequest> getById() {
        return null;
    }

    @Override
    public Result add(JobRequestAddDto jobRequestAdd) {
        JobRequest jobRequestEntity = jobRequestMapper.toJobRequestAdd(jobRequestAdd);
        jobRequestEntity.setRequestStatus(RequestStatus.WAITING);
        jobRequestRepository.save(jobRequestEntity);
        return new SuccessResult(jobRequestEntity.getJobRequestId());
    }

    @Override
    public Result update(JobRequestUpdateDto jobRequestUpdate) {
        JobRequest jobRequestEntity = jobRequestRepository.getById(jobRequestUpdate.getJobRequestId());
        if (jobRequestEntity==null || jobRequestEntity.getRequestStatus()==null)
            return new ErrorResult("Jobrequst cannot found in database.");
        jobRequestMapper.update(jobRequestEntity,jobRequestUpdate);
        jobRequestEntity.setRequestStatus(RequestStatus.WAITING);
        jobRequestRepository.save(jobRequestEntity);
        return new SuccessResult(ResultMessage.CUSTOMER_ADDED);
    }

    @Override
    public Result setStatus(Integer JobRequestId, RequestStatus requestStatus) {
        JobRequest jobRequestEntity = jobRequestRepository.getById(JobRequestId);
        jobRequestEntity.setRequestStatus(requestStatus);
        jobRequestRepository.save(jobRequestEntity);
        return new SuccessResult();
    }


}
