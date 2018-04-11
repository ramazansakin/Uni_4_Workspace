<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="yorumSil.aspx.cs" Inherits="BlogPortalProjesi.yorumSil" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:SqlDataSource ID="yorumSilDataSource" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" ProviderName="<%$ ConnectionStrings:ConnectionString2.ProviderName %>" DeleteCommand="DELETE FROM yorumlar WHERE yorum_ID=@ID" SelectCommand="select * from yorumlar">
            <DeleteParameters>          
            <asp:Parameter Name="ID"  />
            </DeleteParameters>
        </asp:SqlDataSource>
    
    </div>
    </form>
</body>
</html>
