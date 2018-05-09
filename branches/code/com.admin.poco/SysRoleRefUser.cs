
using Dapper;
namespace com.admin.poco
{
    //角色-用户表
    public class SysRoleRefUser
    {
        /// <summary>
        /// RefId
        /// </summary>
        [Key]
        public int RefId { get; set; }

        /// <summary>
        /// 用户ID
        /// </summary>
        public int? UserId { get; set; }

        /// <summary>
        /// 角色ID
        /// </summary>
        public int? RoleId { get; set; }

    }
}

