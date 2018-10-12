using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace Perlin_noise
{
    

    class Program
    {
        static void Main(string[] args)
        {
            var noise = new PerlinNoise();
            noise.Noise();
        }
    }

    public struct DPoint
    {
        public double X;
        public double Y;
        public DPoint(double x,double y)
        {
            X = x;
            Y = y;
        }
    }

    class PerlinNoise
    {
        Bitmap picture;
        public string Name { get; set; }
        byte[] table;

        public PerlinNoise(string name = "Picture1.bmp")
        {
            try
            {
                picture = new Bitmap(name);
                Name = name;
                Console.WriteLine($"Was loaded {Name}");
                FillTable();
            }
            catch
            {
                Console.WriteLine("Name of picture is wrong. loaded default picture");
                Name = "Picture1.bmp";
                try
                {
                    picture = new Bitmap(Name);
                }
                catch
                {
                    Console.WriteLine("Cannot open Picture1.bmp. Plese put it in the same folder with .exe file");
                    return;
                }
                Console.WriteLine($"Was loaded {Name}");
                FillTable();
            }
        }

        private void FillTable()
        {
            table = new byte[picture.Height+picture.Width];
            var rand = new Random();
            rand.NextBytes(table);
        }

        public void Noise(int oct = 5)
        {
            oct = oct < 5 ? 5 : oct;
            for (int i = 0; i < picture.Width; i +=oct)
                for (int j = 0; j < picture.Height; j +=oct)
                {
                    for (int k = 0; k < oct; k++)
                    {
                        if (i + k >= picture.Width)
                            continue;
                        for (int l = 0; l < oct; l++)
                        {
                            if (j + l >= picture.Height)
                                continue;

                            DPoint topLeftVec = GetPseudoRandomVector(i, j);
                            DPoint topRightVec = GetPseudoRandomVector(i + oct, j);
                            DPoint bottomLeftVec = GetPseudoRandomVector(i, j + oct);
                            DPoint bottomRightVec = GetPseudoRandomVector(i + oct, j + oct);

                            double paramX = i%oct / (double)oct;
                            double paramY = j%oct / (double)oct;

                            DPoint fromTopLeftVec = new DPoint(paramX, paramY);
                            DPoint fromTopRightVec = new DPoint(paramX, 1 - paramY);
                            DPoint fromBottomLeftVec = new DPoint(1 - paramX, paramY);
                            DPoint fromBottomRightVec = new DPoint(1 - paramX, 1 - paramY);

                            double dotTopLeftVec = Dot(topLeftVec, fromTopLeftVec);
                            double dotTopRightVec = Dot(topRightVec, fromTopRightVec);
                            double dotBottomLeftVec = Dot(bottomLeftVec, fromBottomLeftVec);
                            double dotBottomRightVec = Dot(bottomRightVec, fromBottomRightVec);

                            paramX = Modify(paramX);
                            paramY = Modify(paramY);

                            double interTop = LInterp(dotTopLeftVec, dotBottomRightVec, paramX);
                            double interBottom = LInterp(dotBottomLeftVec, dotBottomRightVec, paramX);

                            double interY = LInterp(interTop, interBottom, paramY);

                            var px = picture.GetPixel(i , j );
                            //picture.SetPixel(i + k, j + l, Color.FromArgb(Convert.ToInt32(interY * 100000000)));
                            picture.SetPixel(i, j, Color.FromArgb(GetChangedInt(px,interY)));

                        }
                    }
                }
            picture.Save("NewPicture.bmp");
        }

        DPoint GetPseudoRandomVector(int x, int y)
        {
            DPoint res ;
            var temp = (x * 3001 + y * 7) * 307 & (picture.Height + picture.Width-1);
            temp = table[temp] & 7;
            switch(temp)
            {
                case 0:
                    res = new DPoint(1, 0);
                    break;
                case 1:
                    res = new DPoint(0, 1);
                    break;
                case 2:
                    res = new DPoint(-1, 0);
                    break;
                case 3:
                    res = new DPoint(0, -1);
                    break;
                case 4:
                    res = new DPoint(Math.Cos(-0.66), Math.Sin(-0.66));
                    break;
                case 5:
                    res = new DPoint(Math.Cos(1.12), Math.Sin(1.12));
                    break;
                case 6:
                    res = new DPoint(Math.Cos(2.57), Math.Sin(-2.57));
                    break;
                default:
                    res = new DPoint(Math.Cos(0.185398), Math.Sin(0.185398));
                    break;
            }
            return res;
        }

        //linear interpolation
        double LInterp(double left, double right, double param)
            => (right + left) * param - left;

        double Modify(double t) 
            => t * t * (3.0 - 2.0 * t);

        int GetChangedInt(Color color, double mul)
        {
            int R = Convert.ToInt32(color.R * mul);
            int G = Convert.ToInt32(color.G * mul);
            int B = Convert.ToInt32(color.B * mul);
            return R << 16 | G << 8 | B;
        }

        int GetInt(Color color){
            return (int) color.R<<16| (int)color.G << 8 | (int) color.B;
        }

        //scalar mul
        double Dot(DPoint vec1, DPoint vec2) 
            =>vec1.X*vec2.X + vec1.Y*vec2.Y;
        
    }

    class F
    {

    }


   
}
