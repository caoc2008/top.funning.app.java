package top.funning.app.service.shop.modify.p12;

import net.sf.oval.constraint.NotNull;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.multipart.MultipartFile;
import top.funning.app.config.C;
import top.funning.app.database.dal.ShopDAL;
import top.funning.app.database.table.Shop;
import top.funning.app.service.FnService;
import top.knxy.library.ServiceException;
import top.knxy.library.utils.ServiceUtils;

import java.io.File;
import java.io.FileOutputStream;

public class M1031 extends FnService {
    @NotNull
    public MultipartFile file;

    @Override
    protected void run() throws Exception {

        String fileName = ServiceUtils.getUUid();
        String filePath = C.WCPay.p12Path;
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileName);
            out.write(file.getBytes());
            out.flush();

            SqlSession sqlSession = getSqlSession();
            ShopDAL dal = sqlSession.getMapper(ShopDAL.class);
            Shop shop = dal.get(shopId);
            shop.setP12FileName(fileName);
            int row = dal.update(shop);
            sqlSession.commit();
            if (row < 1) {
                throw new ServiceException("row < 1");
            }
            createSuccess(this);
        } catch (Exception e) {
            throw e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }
}
