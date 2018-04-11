using System;

public partial class Login : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
        if (Session["UserId"] != null)
            Response.Redirect("/");
    }

    protected void btnLogin_Click(object sender, EventArgs e)
    {
        var userName = txtUserName.Text;
        var passWord = txtPassword.Text;
        var userId = Convert.ToInt32(AdoFunc.GetDataCell("select count(UserId) from Users where UserName='" + userName + "' and Password=" + passWord));
        if (userId > 0)
        {
            Session["UserId"] = userId;
            Response.Redirect("/");
        }
        else
        {
            lblInfo.Text = "Kullanıcı adı yada şifre yanlış";
            lblInfo.Visible = true;
        }

    }
}