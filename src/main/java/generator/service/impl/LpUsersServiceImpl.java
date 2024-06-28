package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.LpUsers;
import generator.service.LpUsersService;
import generator.mapper.LpUsersMapper;
import org.springframework.stereotype.Service;

/**
* @author lzx
* @description 针对表【lp_users】的数据库操作Service实现
* @createDate 2024-06-21 18:31:18
*/
@Service
public class LpUsersServiceImpl extends ServiceImpl<LpUsersMapper, LpUsers>
    implements LpUsersService{

}




