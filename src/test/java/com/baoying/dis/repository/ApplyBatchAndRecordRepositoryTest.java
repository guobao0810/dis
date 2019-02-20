package com.baoying.dis.repository;

import com.baoying.dis.assets.utils.JsonUtil;
import com.baoying.dis.entity.ApplyBatch;
import com.baoying.dis.entity.ApplyRecord;
import com.baoying.dis.entity.dict.ApplyBatchStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试 批次 applyBatchRepository
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyBatchAndRecordRepositoryTest {
    @Resource
    private ApplyBatchRepository applyBatchRepository;

    @Resource
    private ApplyRecordRepository applyRecordRepository;

    // 构造一个 batch
    private ApplyBatch newApplyBatch() {
        ApplyBatch applyBatch = new ApplyBatch();
        applyBatch.setApplicant("maomh");
        applyBatch.setBatchNo("201901181205666");
        applyBatch.setDirPath("/abc/haha");
        applyBatch.setZipPath("/abc/haha/pdfs.zip");
        applyBatch.setStatus(ApplyBatchStatus.UPLOAD.getValue());
        return applyBatch;
    }

    private ApplyRecord newApplyRecord() {
        ApplyRecord applyRecord = new ApplyRecord();
        applyRecord.setAcountId("11023847");
        applyRecord.setMonth("201909");
        applyRecord.setSeq("9900");
        applyRecord.setCompName("逗逼工作室");
        applyRecord.setMoney("1049.45");
        applyRecord.setZhcnMoney("壹仟零肆拾玖圆肆角伍分");
        applyRecord.setApplyDate("2019-02-20");
        return applyRecord;
    }

    @Test
    @Transactional
    public void saveBatch() {

        ApplyBatch applyBatch = newApplyBatch();
        applyBatchRepository.save(applyBatch);
        System.out.println(JsonUtil.toJson(applyBatch, true));
        Assert.assertNotNull(applyBatch.getId());
    }



    @Test
    @Transactional
    public void saveRecordAndList() {
        ApplyBatch applyBatch = newApplyBatch();
        applyBatchRepository.save(applyBatch);

        ApplyRecord applyRecord = newApplyRecord();
        applyRecord.setApplyBatch(applyBatch);
        applyRecordRepository.save(applyRecord);

        List<ApplyRecord> list = applyRecordRepository.findAllByBatchNo(applyBatch.getBatchNo());
        System.out.println(JsonUtil.toJson(list, true));
    }

    @Test
    @Transactional
    @Rollback(false)
    public void test5000RecordWrite() {
        ApplyBatch applyBatch = newApplyBatch();
        applyBatchRepository.save(applyBatch);

        // 5000
        List<ApplyRecord> applyRecords = new LinkedList<>();
        for (int i = 0; i < 5000; ++i) {
            ApplyRecord applyRecord = newApplyRecord();
            applyRecord.setApplyBatch(applyBatch);
            applyRecords.add(applyRecord);
        }

        System.out.println("===== 准备保存 5000 条数据 =====");
        applyRecordRepository.saveAll(applyRecords);
    }
}
