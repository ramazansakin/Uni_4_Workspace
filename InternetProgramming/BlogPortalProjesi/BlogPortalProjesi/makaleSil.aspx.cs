using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace BlogPortalProjesi
{
    public partial class makaleSil : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null || Request.QueryString.Count <= 0)
            {
                Response.Redirect("Anasayfa.aspx");
            }
            else
            {
                try
                {
                    SqlDataSource1.Delete();
                    Response.Redirect("adminPanel.aspx");
                }
                catch (Exception ex)
                {
                    Response.Write(ex.Message);
                }
            }
        }
    }
}