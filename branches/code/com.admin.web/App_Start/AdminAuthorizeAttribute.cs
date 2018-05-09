using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace com.admin.web
{
    [AttributeUsage(AttributeTargets.Method | AttributeTargets.Class, Inherited = true, AllowMultiple = true)]
    public class AdminAuthorizeAttribute : FilterAttribute, IAuthorizationFilter
    {
        public virtual void OnAuthorization(AuthorizationContext filterContext)
        {
            if (filterContext.ActionDescriptor.ControllerDescriptor.IsDefined(typeof(AllowAnonymousAttribute), true))
            {
                return;
            }
            if (filterContext.ActionDescriptor.IsDefined(typeof(AllowAnonymousAttribute), true))
            {
                return;
            }

            filterContext.Result = new RedirectResult("/Home/Login/");
            //var user = AccountSession.Account;
            //if (user == null)
            //{
            //    filterContext.Result = new RedirectResult("/Home/Login/");
            //}
            //else
            //{
            //    base.OnAuthorization(filterContext);
            //    if (filterContext.HttpContext.Response.StatusCode == 403)
            //    {
            //        filterContext.Result = new RedirectResult("/Home/NotAuthorized");
            //    }
            //}
        }

        protected bool AuthorizeCore(HttpContextBase httpContext)
        {
            //ViewContext
            var controller = httpContext.Request.RequestContext.RouteData.Values["controller"].ToString();
            var action = httpContext.Request.RequestContext.RouteData.Values["action"].ToString();
            var isAllowed = true;// IsAllowed(AccountSession.Account, controller, action);
            if (!isAllowed)
            {
                var wrapper = new HttpRequestWrapper(HttpContext.Current.Request);
                if (wrapper.IsAjaxRequest())
                {
                    HttpContext.Current.Response.Write("NotAuthorized");
                    HttpContext.Current.Response.StatusCode = 403;
                    HttpContext.Current.Response.End();
                }
                else
                {
                    HttpContext.Current.Response.StatusCode = 403;
                }
            }
            return true;
            // return isAllowed || base.AuthorizeCore(httpContext);
        }


    }
}