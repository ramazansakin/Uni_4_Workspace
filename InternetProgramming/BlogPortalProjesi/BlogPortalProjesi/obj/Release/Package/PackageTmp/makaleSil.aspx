<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="makaleSil.aspx.cs" Inherits="BlogPortalProjesi.makaleSil" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" ProviderName="<%$ ConnectionStrings:ConnectionString2.ProviderName %>" SelectCommand="SELECT * FROM [makaleler]" DeleteCommand="DELETE FROM makaleler WHERE makale_ID=@ID">
            <DeleteParameters>
                <asp:QueryStringParameter Name="ID" QueryStringField="ID" />
            </DeleteParameters>
        </asp:SqlDataSource>
    <div>
    
    </div>
    </form>
</body>
</html>
