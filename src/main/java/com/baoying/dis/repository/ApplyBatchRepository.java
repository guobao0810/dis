package com.baoying.dis.repository;

import com.baoying.dis.assets.base.BaseRepository;
import com.baoying.dis.entity.ApplyBatch;
import com.baoying.dis.entity.ApplyRecord;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ApplyBatchRepository extends BaseRepository<ApplyBatch> {

    /**
     * 根据批次号查找申请批次
     *
     * @param batchNo
     * @return
     */
    Optional<ApplyBatch> findByBatchNo(String batchNo);
}
