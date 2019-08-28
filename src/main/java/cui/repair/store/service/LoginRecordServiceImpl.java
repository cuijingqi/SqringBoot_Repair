package cui.repair.store.service;

import cui.repair.store.dao.LoginRecordDao;
import cui.repair.store.entity.LoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRecordServiceImpl implements LoginRecordService {


    @Autowired
    LoginRecordDao loginRecordDao;


    @Override
    public void insert(LoginRecord loginRecord) {

        loginRecordDao.insert(loginRecord);
    }

    @Override
    public void delete(String userName) {
        loginRecordDao.delete(userName);
    }

    @Override
    public LoginRecord getLatestRecordBy(String username) {
        return loginRecordDao.getLatestRecordBy(username);
    }

    @Override
    public LoginRecord getLogin2TimesBy(String userName) {
        return loginRecordDao.getLogin2TimesBy(userName);
    }

    @Override
    public boolean isExistLoginRecordBy(String username) {
        int cnt = loginRecordDao.isExistLoginRecordBy(username);
        if(cnt>=1){
            return true;
        }
        return false;
    }
}
