using System.Data;
using System.Data.SqlClient;

namespace com.admin.common
{
    public class Db_base
    {
        public IDbConnection CreateInstance()
        {
            return new SqlConnection(ConfigHelper.GetConnectionString("DefaultConnection"));
        }
    }
}
