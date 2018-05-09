using System;
using System.Configuration;

namespace com.admin.common
{
    public class ConfigHelper
    {
        /// <summary>
        /// 读取设置
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public static string GetAppSettingValue(string key)
        {
            try
            {
                return ConfigurationManager.AppSettings[key];
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// 读取连接
        /// </summary>
        /// <param name="key"></param>
        /// <returns></returns>
        public static string GetConnectionString(string key)
        {
            try
            {
                return ConfigurationManager.ConnectionStrings[key].ConnectionString;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}
