package com.baoying.dis.sign;

import com.baoying.dis.entity.ApplyRecord;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.text.MessageFormat;

/**
 * 收入证明 模板
 */
public class IncomeProofTemplate {
    private static final Font bold;
    private static  final Font small;
    private static final Font normal;
    static {
        try {
            BaseFont song = BaseFont.createFont("/font/SIMSUN.TTC,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            bold = new Font(song, 22f, Font.BOLD);
            small = new Font(song, 10f, Font.NORMAL);
            normal = new Font(song, 16f, Font.NORMAL);
        } catch (Exception e) {
            throw new RuntimeException("中文字体缺失.", e);
        }

    }

    // 标题
    private static Paragraph title(String str) {
        Paragraph p = new Paragraph(str, bold);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        return p;
    }

    // 头注
    private static Paragraph note(String str) {
        Paragraph p = new Paragraph(str, small);
        p.setFirstLineIndent(small.getSize() * 14);
        return p;
    }

    // 正文
    private static Paragraph body(String str) {
        return new Paragraph(str, normal);
    }

    /**
     * 根据申请明细创建 收入证明 原始文件
     *
     * @param applyRecord
     * @return
     * @throws Exception
     */
    public static byte[]  createPdf(ApplyRecord applyRecord) throws Exception{
        Document document = new Document(PageSize.A4, 90, 90, 72, 72);
        document.addTitle("收入证明");
        document.addAuthor("葆婴有限公司");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        document.add(title("证  明"));
        // 编号：  «月份» «ID号»  «序号»
        document.add(note(MessageFormat.format("编号：  {0}{1}{2}", applyRecord.getMonth(), applyRecord.getAcountId(), applyRecord.getSeq())));
        // 日期：  «日期»
        document.add(note(MessageFormat.format("日期：  {0}", applyRecord.getApplyDate())));
        document.add(Chunk.NEWLINE);
        document.add(body(MessageFormat.format("    现有（商号名:{0}）为我公司提供（咨询、服务、产品推广服务、个别推广服务），本月产生的（咨询费、服务费、产品推广服务费、个别推广服务费）", applyRecord.getCompName())));
        document.add(Chunk.NEWLINE);
        document.add(body(MessageFormat.format("费用金额共计人民币小写：{0}元。", applyRecord.getMoney())));
        document.add(body(MessageFormat.format("费用金额共计人民币大写：{0}。", applyRecord.getZhcnMoney())));
        document.add(Chunk.NEWLINE);
        document.add(body("付款方式：委托第三方银行转帐"));
        document.add(Chunk.NEWLINE);
        document.add(body("该证明仅供经销商提供给税务机关代开或向税务机关购买同等金额发票使用。"));
        document.add(Chunk.NEWLINE);
        document.add(body("付款方：      葆婴有限公司"));
        document.add(body("开户银行：    招商银行北京分行朝阳门支行"));
        document.add(body("账    号：    862180181610001"));
        document.add(body("纳税人识别号：91110302710920362G"));
        document.add(body("地址：北京市北京经济技术开发区景园街9号院1号楼"));
        document.add(body("电话：010-87837665"));
        document.close();
        return outputStream.toByteArray();
    }

/*
    public static void main(String[] args) throws Exception {
        ApplyRecord applyRecord = new ApplyRecord();
        applyRecord.setAcountId("11023847");
        applyRecord.setMonth("201909");
        applyRecord.setSeq("9900");
        applyRecord.setCompName("逗逼工作室");
        applyRecord.setMoney("1049.45");
        applyRecord.setZhcnMoney("壹仟零肆拾玖圆肆角伍分");
        applyRecord.setApplyDate("2019-02-20");
        byte[] pdf = createPdf(applyRecord);
        FileOutputStream os = new FileOutputStream("/Users/mhmao/Repos/aaa.pdf");
        os.write(pdf);
        os.close();
    }
*/
}
