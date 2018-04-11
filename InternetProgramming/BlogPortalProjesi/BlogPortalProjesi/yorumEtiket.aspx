<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="yorumEtiket.aspx.cs" Inherits="BlogPortalProjesi.yorumEtiket" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:SqlDataSource ID="istenmeyenYap" runat="server" ConnectionString="<%$ ConnectionStrings:ConnectionString2 %>" SelectCommand="select * from yorumlar" UpdateCommand="UPDATE yorumlar SET istenmeyen=1 WHERE yorum_ID=@ID">
            <UpdateParameters>
                <asp:Parameter Name="ID" />
            </UpdateParameters>
        </asp:SqlDataSource>
    
    </div>
    </form>
</body>
</html>
