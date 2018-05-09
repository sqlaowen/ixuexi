
using Dapper;
namespace com.admin.poco
{
    //角色-菜单表
    public class SysRoleRefMenu
    {
        /// <summary>
        /// RefId
        /// </summary>
        [Key]
        public int RefId { get; set; }

        /// <summary>
        /// 菜单id
        /// </summary>
        public int? MenuId { get; set; }

        /// <summary>
        /// 角色ID
        /// </summary>
        public int? RoleId { get; set; }

    }
}

