using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using System.Data.SqlClient;

namespace BlogPortalProjesi
{
    public partial class adminPanel : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null)
            {
                Response.Redirect("Anasayfa.aspx");
            }
        }
        

        protected void dologout_Click(object sender, EventArgs e)
        {
            Response.Cookies["yonetici"].Expires = DateTime.Now.AddDays(-2);
            Response.Redirect("adminPanel.aspx");
        }
    }
}