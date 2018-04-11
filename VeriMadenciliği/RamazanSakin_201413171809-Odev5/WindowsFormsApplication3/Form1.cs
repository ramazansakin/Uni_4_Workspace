using System;
using System.Drawing;
using System.Windows.Forms;


namespace WindowsFormsApplication3
{
    public partial class Form1 : Form
    {

        Gozlem[] gozlemler;
        int size ;
        int k;
        Point referansPoint;

        public Form1()
        {
            InitializeComponent();
            gozlemler = new Gozlem[100];
            size = 0;
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }


        private double getOklitDistance( Point d1, Point d2 )
        {
            return Math.Sqrt(  Math.Pow( (d1.X - d2.X) , 2 ) + Math.Pow((d1.Y - d2.Y ), 2));
        }

        private void button1_Click(object sender, EventArgs e)
        {
            Gozlem g = new Gozlem();
            Point p = new Point();
            p.X =  Int32.Parse(deger1TB.Text.ToString());
            p.Y = Int32.Parse(deger2TB.Text.ToString());


            g.setPoint(p);
            g.setOrder(size);

            if (negRdBtn.Checked)
            {
                g.setStatus('-');
            }
            else
                g.setStatus('+');

            gozlemler[size] = g;
            ++size;

            listeyeEkle();
            
        }

        private void listeyeEkle()
        {
            ListViewItem lvi = new ListViewItem( ""+gozlemler[size-1].getOrder() );
            lvi.SubItems.Add(""+gozlemler[size - 1].getPoint().X);
            lvi.SubItems.Add("" + gozlemler[size - 1].getPoint().Y);
            lvi.SubItems.Add(""+gozlemler[size - 1].getStatus());

            DegerlerLw.Items.Add(lvi);
        }


        private void button2_Click(object sender, EventArgs e)
        {
            k = Int32.Parse(kValue.Text.ToString());

            referansPoint.X = Int32.Parse(istenilenX1.Text.ToString());
            referansPoint.Y = Int32.Parse(istenilenX2.Text.ToString());
            

            double[] distances = new double[size];

            for (int i = 0; i < size; ++i)
                gozlemler[i].mesafe = getOklitDistance(referansPoint, gozlemler[i].getPoint());


            for (int i = 0; i < size-1; ++i )
            {
                for( int j = i+1 ; j < size; ++j)
                {
                    if( gozlemler[j].mesafe < gozlemler[i].mesafe )
                    {
                        Gozlem temp = gozlemler[i];
                        gozlemler[i] = gozlemler[j];
                        gozlemler[j] = temp;
                    }
                }                       
            }


            int pos = 0, neg = 0;

            for (int i = 0; i < size; ++i)
            {

                ListViewItem lvi = new ListViewItem("" + gozlemler[i].getOrder());

                lvi.ForeColor = Color.Black;
                if (i < k)
                {
                    lvi.ForeColor = Color.Red;
                    if (gozlemler[i].getStatus() == '-')
                        neg++;
                    else
                        pos++;
                }
                lvi.SubItems.Add("" + gozlemler[i].getPoint().X);
                lvi.SubItems.Add("" + gozlemler[i].getPoint().Y);
                lvi.SubItems.Add("" + gozlemler[i].mesafe.ToString("F2") );
                lvi.SubItems.Add("" + gozlemler[i].getStatus());

                sonuclarLw.Items.Add(lvi);
            }

            

            if (pos > neg)
                resultK.Text = "POZITIF";
            else
                resultK.Text = "NEGATIF";
        }

        
    }
}
