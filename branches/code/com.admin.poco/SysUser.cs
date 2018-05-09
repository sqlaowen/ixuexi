using System;
using Dapper;

namespace com.admin.poco
{
    //用户表
    public class SysUser
    {
        /// <summary>
        /// 用户ID
        /// </summary>
        [Key]
        public int UserId { get; set; }

        /// <summary>
        /// 真实名
        /// </summary>
        public string Name { get; set; }

        /// <summary>
        /// 性别 1:男  2:女
        /// </summary>
        public int? Sex { get; set; }

        /// <summary>
        /// 账号
        /// </summary>
        public string Account { get; set; }

        /// <summary>
        /// 密码
        /// </summary>
        public string Pwd { get; set; }

        /// <summary>
        /// 状态
        /// </summary>
        public int? State { get; set; }

        /// <summary>
        /// 备注
        /// </summary>
        public string Note { get; set; }

        /// <summary>
        /// 创建时间
        /// </summary>
        public DateTime? CreateTime { get; set; }

        /// <summary>
        /// 创建人
        /// </summary>
        public int? CreateUser { get; set; }

        /// <summary>
        /// 操作时间
        /// </summary>
        public DateTime? UpdateTime { get; set; }

        /// <summary>
        /// 操作人
        /// </summary>
        public int? UpdateUser { get; set; }

    }
}

