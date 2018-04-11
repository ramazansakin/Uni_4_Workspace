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
    public partial class makaleDetay : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.QueryString.Count <= 0)
            {
                Response.Write("<font color=red>QueryString Girilmedi!");
                Response.Redirect("Anasayfa.aspx");
            }
        }

        protected void btn_yrm_tmzle_Click(object sender, EventArgs e)
        {
          
        }

        protected void btn_yrm_gonder_Click(object sender, EventArgs e)
        {
            if (txt_yrmcu_adi.Text == "" || txt_yrm_icerigi.Text == "")
            {
                string ID = Request.QueryString["ID"].ToString();
                Response.Redirect("makaleDetay.aspx?ID=" + ID);
            }
            else {
                string ID = Request.QueryString["ID"].ToString();
                SqlConnection baglanti = new SqlConnection(Fonksiyonlar.connectionString());
                try
                {
                    baglanti.Open();
                    SqlCommand komut = new SqlCommand();
                    komut.Connection = baglanti;
                    komut.CommandType = CommandType.Text;
                    komut.CommandText = "INSERT INTO yorumlar (makale_ID,yorum_tarihi,yorumcu_adi,yorumcu_eposta,yorum_icerigi) VALUES (@makale_ID,@yorum_tarihi,@yorumcu_adi,@yorumcu_eposta,@yorum_icerigi)";
                    komut.Parameters.AddWithValue("@makale_ID", ID);
                    komut.Parameters.AddWithValue("@yorum_tarihi",DateTime.Now.ToString());
                    komut.Parameters.AddWithValue("@yorumcu_adi", txt_yrmcu_adi.Text);
                    komut.Parameters.AddWithValue("@yorumcu_eposta", txt_yrmcu_eposta.Text);
                    komut.Parameters.AddWithValue("@yorum_icerigi", txt_yrm_icerigi.Text);
                    komut.ExecuteNonQuery();
                    Response.Redirect("makaleDetay.aspx?ID=" + ID);
                }
                catch (Exception ex)
                {
                    Response.Write("<font color=red>HATA:</font>Yorum eklenirken hata meydana geldi!<br>" + ex.Message);
                }

                finally
                {
                    if (baglanti.State == ConnectionState.Open)
                    {
                        baglanti.Close();
                    }
                }
            }
        }
     }
 }
