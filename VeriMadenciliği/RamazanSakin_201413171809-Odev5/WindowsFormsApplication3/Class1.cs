using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace WindowsFormsApplication3
{
    class Gozlem
    {
        private Point point;
        private char durum;
        private int sira;
        public double mesafe;
        public double isMin;

        // default constructor
        public Gozlem()
        {
            point = new Point();
            durum = '*';
            sira = -1;

        }

        // Constructor-2
        public Gozlem( Point p, char durum, int sira  )
        {
            this.point = p;
            this.durum = durum;
            this.sira = sira;
        }

        public Point getPoint()
        {
            return point;
        }

        public void setPoint( Point p )
        {
            point = p;
        }

        public char getStatus()
        {
            return durum;
        }

        public void setStatus( char s )
        {
            durum = s;
        }

        public int getOrder()
        {
            return sira;
        }

        public void setOrder( int o )
        {
            sira = o;
        }


    }
}
