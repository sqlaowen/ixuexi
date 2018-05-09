using System;
using Dapper;
using System.Collections.Generic;

namespace com.admin.poco
{
    //菜单表
    public class SysMenu
    {
        /// <summary>
        /// 菜单id
        /// </summary>
        [Key]
        public int MenuId { get; set; }

        /// <summary>
        /// 菜单名
        /// </summary>
        public string Name { get; set; }

        /// <summary>
        /// 菜单URL
        /// </summary>
        public string Url { get; set; }

        /// <summary>
        /// 层级
        /// </summary>
        public int? Level { get; set; }

        /// <summary>
        /// 上级ID
        /// </summary>
        public int? PId { get; set; }

        /// <summary>
        /// 顺序
        /// </summary>
        public int? Sequ { get; set; }

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


        /// <summary>
        /// 上级（非持久化）
        /// </summary>      
        public SysMenu Parent { get; set; }

        /// <summary>
        /// 下级（非持久化）
        /// </summary>            
        public List<SysMenu> Children { get; set; }

    }
}

