using System;
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
            noise.Noise(30);
            

        }
    }

    public struct DPoint
    {
        public double X;
        public double Y;
        public DPoint(double x, double y)
        {
            X = x;
            Y = y;
        }
    }

    class PerlinNoise
    {

        Bitmap picture;
        public string Name { get; set; }
       
        DPoint[,] vectors;
        Random rand = new Random();

        public PerlinNoise(string name = "Picture1.bmp")
        {
            try
            {
                picture = new Bitmap(name);
                Name = name;
                Console.WriteLine($"Was loaded {Name}");
              
            }
            catch
            {
                Console.WriteLine("Name of picture is wrong. loading default picture");
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
               
            }
        }

     

        public void Noise(int oct = 5)
        {
            oct = oct < 5 ? 5 : oct;

            CreatePseudoRandomVectors(oct);
            for (int i = 0; i < picture.Width; i += oct)
                for (int j = 0; j < picture.Height; j += oct)
                {
                    DPoint topLeftVec = vectors[i / (oct + 1), j / (oct + 1)];
                    DPoint topRightVec = vectors[i / (oct + 1) + 1, j / (oct + 1)];
                    DPoint bottomLeftVec = vectors[i /( oct + 1), j / (oct + 1) + 1];
                    DPoint bottomRightVec = vectors[i /( oct + 1) + 1, j / (oct + 1) + 1];

                    for (int k = 0; k < oct; k++)
                    {
                        if (i + k >= picture.Width)
                            continue;
                        for (int l = 0; l < oct; l++)
                        {
                            if (j + l >= picture.Height)
                                continue;

                            double paramX =  k / (double)oct;
                            double paramY = l / (double)oct;
                            //Mod(paramX);
                            //Mod(paramX);
                            DPoint fromTopLeftVec = new DPoint(paramX, -paramY);
                            DPoint fromTopRightVec = new DPoint(-paramX, -(1 - paramY));
                            DPoint fromBottomLeftVec = new DPoint(1 - paramX, paramY);
                            DPoint fromBottomRightVec = new DPoint(-(1 - paramX), 1 - paramY);

                            double dotTopLeftVec = Dot(topLeftVec, fromTopLeftVec);
                            double dotTopRightVec = Dot(topRightVec, fromTopRightVec);
                            double dotBottomLeftVec = Dot(bottomLeftVec, fromBottomLeftVec);
                            double dotBottomRightVec = Dot(bottomRightVec, fromBottomRightVec);
                            
                            double interTop = LInterp(dotTopLeftVec, dotTopRightVec,Mod(paramX));
                            double interBot = LInterp(dotBottomLeftVec, dotBottomRightVec, Mod(paramX));

                            double interY = LInterp(interTop, interBot, Mod(paramY));
                            
                            if (interY > 0)
                            {
                                picture.SetPixel(i + k, j + l, Color.FromArgb((int)(picture.GetPixel(i + k, j + l).R *  interY) % 255,
                                                                              (int)(picture.GetPixel(i + k, j + l).G *  interY) % 255,
                                                                              (int)(picture.GetPixel(i + k, j + l).B *  interY) % 255));
                            }
                            else
                            {
                                picture.SetPixel(i + k, j + l, Color.FromArgb((int)(picture.GetPixel(i + k, j + l).R *  Math.Abs(interY)) % 255,
                                                                              (int)(picture.GetPixel(i + k, j + l).G *  Math.Abs(interY)) % 255,
                                                                              (int)(picture.GetPixel(i + k, j + l).B *  Math.Abs(interY)) % 255));
                            }
                            
                        }
                    }
                }
            picture.Save("NewPicture.bmp");
        }

        private void CreatePseudoRandomVectors(int oct)
        {
            int h = picture.Height % oct == 0 ? picture.Height / oct : picture.Height / oct + 1;
            int w = picture.Width % oct == 0 ? picture.Width / oct : picture.Width / oct + 1;

            vectors = new DPoint[w + 1, h + 1];

            for (int i = w; i >= 0; i--)
            {
                for (int j = h; j >= 0; j--)
                {
                    vectors[i, j] = GetPseudoRandomVector();
                }
            }
        }

        DPoint GetPseudoRandomVector()
        {
            DPoint res;                             
            double RandomValue1 = rand.NextDouble();
            Double RandomValue2 = RandomValue1 * (2 *Math.PI);
            res.X = Math.Cos(RandomValue2);
            res.Y = Math.Sin(RandomValue2);
            return res;
        }

        
        double LInterp(double left, double right, double param)
            => left + param * (right - left);

        double Mod(double t)
             //=> (1 - Math.Cos(t * Math.PI)) / 2;
             =>  t * t * t * (t * (t * 6 - 15) + 10);

        int GetChangedInt(Color color, double mul)
        {
            int R = Convert.ToInt32(Math.Abs(color.R * mul));
            int G = Convert.ToInt32(Math.Abs(color.G * mul));
            int B = Convert.ToInt32(Math.Abs(color.B * mul));
            return R << 16 | G << 8 | B;
        }

        int GetInt(Color color)
        {
            return (int)color.R << 16 | (int)color.G << 8 | (int)color.B;
        }

        
        double Dot(DPoint vec1, DPoint vec2)
            => vec1.X * vec2.X + vec1.Y * vec2.Y;

    }
}
//class MyRandom : Random
//{
//    public double NextDouble(double MinValue, double MaxValue)
//    {


//        int RandomValueInt;
//        double RandomValueDouble;
//        Random rand = new Random();
//        RandomValueInt = rand.Next((int)MinValue, (int)MaxValue + 1);
//        for (; ; )
//        {
//            RandomValueDouble = rand.NextDouble();
//            if (RandomValueInt > 0)
//            {
//                if ((RandomValueInt == (int)MaxValue) && (RandomValueDouble < Math.Abs(MaxValue % (int)MaxValue)))
//                {
//                    return RandomValueInt + RandomValueDouble;

//                }
//                if ((RandomValueInt == (int)MinValue) && (RandomValueDouble > Math.Abs(MinValue % (int)MinValue)))
//                {
//                    return RandomValueInt + RandomValueDouble;

//                }
//            }
//            else
//            {
//                if ((RandomValueInt == (int)MaxValue) && (RandomValueDouble < Math.Abs(MaxValue % (int)MaxValue)))
//                {
//                    return RandomValueInt - RandomValueDouble;

//                }
//                if ((RandomValueInt == (int)MinValue) && (RandomValueDouble < Math.Abs(MinValue % (int)MinValue)))
//                {
//                    return RandomValueInt - RandomValueDouble;
//                }
//            }
//        }



//    }
//}
