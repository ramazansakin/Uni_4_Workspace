<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="onayla.aspx.cs" Inherits="BlogPortalProjesi.onayla" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:SqlDataSource ID="kesinlestir" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" SelectCommand="select * from yorumlar" UpdateCommand="UPDATE yorumlar SET yorum_onay_durumu=1 WHERE yorum_ID=@ID">
            <UpdateParameters>
                <asp:QueryStringParameter DefaultValue="" Name="ID" QueryStringField="ID" />
            </UpdateParameters>
        </asp:SqlDataSource>
    
    </div>
    </form>
</body>
</html>
