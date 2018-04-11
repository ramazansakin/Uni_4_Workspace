using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace BlogPortalProjesi
{
    public partial class yorumSil : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.Cookies["yonetici"] == null)
            {
                Response.Redirect("Anasayfa.aspx");
            }
            else { 
                if (Request.QueryString.Count > 0)
                {
                    yorumSilDataSource.Delete();
                    Response.Redirect("yorumOnayla.aspx");
                }
                else
                {
                    Response.Redirect("Anasayfa.aspx");
                }
          }
        }
    }
}