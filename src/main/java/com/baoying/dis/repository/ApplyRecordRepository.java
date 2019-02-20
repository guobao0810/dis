package com.baoying.dis.repository;

import com.baoying.dis.assets.base.BaseRepository;
import com.baoying.dis.entity.ApplyRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplyRecordRepository extends BaseRepository<ApplyRecord> {

    /**
     * 根据批次号获取该批次下的所有记录
     *
     * @param batchNo
     * @return
     */
    @Query("select a from ApplyRecord a where a.applyBatch.batchNo=?1")
    List<ApplyRecord> findAllByBatchNo(String batchNo);
}
