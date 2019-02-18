package com.baoying.dis.repository;

import com.baoying.dis.assets.utils.JsonUtil;
import com.baoying.dis.entity.ApplyBatch;
import com.baoying.dis.entity.dict.ApplyBatchStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 测试 批次 dao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyBatchRepositoryTest {
    @Resource
    private ApplyBatchRepository dao;

    @Test
    @Rollback
    public void add() {
        ApplyBatch ab = new ApplyBatch();
        ab.setApplicant("maomh");
        ab.setBatchNo("201901181205666");
        ab.setDirPath("/abc/haha");
        ab.setZipPath("/abc/haha/pdfs.zip");
        ab.setStatus(ApplyBatchStatus.UPLOAD.getValue());

        dao.save(ab);

        System.out.println(JsonUtil.toJson(ab, true));
    }
}
