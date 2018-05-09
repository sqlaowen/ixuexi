using Dapper;
using System;

namespace com.admin.poco
{
    //角色表
    public class SysRole
    {
        /// <summary>
        /// 角色ID
        /// </summary>
        [Key]
        public int RoleId { get; set; }

        /// <summary>
        /// 角色名
        /// </summary>
        public string Name { get; set; }

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

