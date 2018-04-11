using System;
using System.Web;
using System.Web.UI;

public partial class Default : Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["UserId"] == null)
            Response.Redirect("/login.aspx");
        List();
    }

    protected void btnLogout_Click(object sender, EventArgs e)
    {
        HttpContext.Current.Session.Clear();
        HttpContext.Current.Session.Abandon();
        Response.Redirect("/Login.aspx");
    }

    public void List()
    {
        rptNews.DataSource = AdoFunc.GetDataTable("select * from News");
        rptNews.DataBind();
    }

    protected void rptNews_ItemCommand(object source, System.Web.UI.WebControls.RepeaterCommandEventArgs e)
    {
        if (e.CommandName == "Publish")
        {
            var data = e.CommandArgument.ToString().ClearSql().Split(',');
            var id = Convert.ToInt32(data[0]);
            var status = data[1] == "True" ? 0 : 1;
            var result = AdoFunc.Cmd("update News set IsActive=" + status + " where NewsId=" + id);
            if (result > 0)
            {
                lblInfo.Text = status == 0 ? "Haber başarılı bir şekilde yayından kaldırıldı" : "Haber başarılı bir şekilde yayınlandı."; ;
                List();
            }
            else
                lblInfo.Text = "Bir hata oluştu lütfen daha sonra tekrar deneyiniz.";

            lblInfo.Visible = true;
        }
    }
}