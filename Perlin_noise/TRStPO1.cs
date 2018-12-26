using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Drawing;
using System.Diagnostics;

namespace lab1
{   // MAIN ----------------------------------
   
    //---------------------------------------------

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
 class ParalelPerlinNiose
{
    int NumberOfProcessors = Environment.ProcessorCount;
    public PerlinNoise[] Noises;
    Bitmap picture;
    public void CloneVectors(DPoint[,] B, DPoint[,] A)
    {
        for (int i = 0; i < B.GetLength(0); i++)
        {
            for (int j = 0; j < B.GetLength(1); j++)
            {
                B[i, j].X = A[i, j].X;
                B[i, j].Y = A[i, j].Y;
            }
        }
    }
    public ParalelPerlinNiose(string name = "Picture1.bmp")
    {
        picture = new Bitmap(name);
        Bitmap[] PartOfPicture = new Bitmap[NumberOfProcessors];
        for (int a = 0; a < NumberOfProcessors; a++)
        {

            PartOfPicture[a] = new Bitmap(picture.Width / NumberOfProcessors, picture.Height, picture.PixelFormat);
            for (int i = 0; i < PartOfPicture[a].Width; i++)
            {
                for (int j = 0; j < PartOfPicture[a].Height; j++)
                {
                    PartOfPicture[a].SetPixel(i, j, picture.GetPixel((picture.Width / NumberOfProcessors * a) + i, j));
                }
            }
        }
        Noises = new PerlinNoise[NumberOfProcessors];

        for (int a = 0; a < NumberOfProcessors; a++)
        {


            Noises[a] = new PerlinNoise(PartOfPicture[a]);
            //CloneVectors(Noises[a].vectors, Noises[0].vectors);
        }
    }

    public void GlueToOne()
    {
        for (int a = 0; a < NumberOfProcessors; a++)
        {
            for (int i = 0; i < Noises[a].picture.Width; i++)
            {
                for (int j = 0; j < Noises[a].picture.Height; j++)
                {
                    picture.SetPixel((picture.Width / NumberOfProcessors * a) + i, j, Noises[a].picture.GetPixel(i, j));
                }
            }
        }

    }

    public void ParalelNoise()
    {
        // var watch = Stopwatch.StartNew();
        Thread[] threads = new Thread[NumberOfProcessors];
        for (int i = 0; i < NumberOfProcessors; i++)
        {
            threads[i] = new Thread(Noises[i].Noise);

            threads[i].Start();

        }
        for (int i = 0; i < NumberOfProcessors; i++)
        {


            threads[i].Join();
            //Noises[i].SavePicture("new1");
        }
        //  watch.Stop();
        //  Console.WriteLine(watch.ElapsedMilliseconds);
        //  Console.ReadKey();
    }

    public void SavePicture()
    {
        picture.Save("new.bmp");

    }
}

class PerlinNoise
{

    public Bitmap picture;
    public string Name { get; set; }
    public int oct = 30;
    public string Lock = "lock";

    void InitOct(int A)
    {
        oct = A;
    }
    public DPoint[,] vectors;

    Random rand = new Random();

    public PerlinNoise(string name = "Picture1.bmp")
    {
        try
        {
            picture = new Bitmap(name);
            CreatePseudoRandomVectors(oct);
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
                CreatePseudoRandomVectors(oct);
            }
            catch
            {
                Console.WriteLine("Cannot open Picture1.bmp. Plese put it in the same folder with .exe file");
                return;
            }
            Console.WriteLine($"Was loaded {Name}");

        }
    }
    public PerlinNoise(Bitmap name1)
    {
        picture = name1;
        CreatePseudoRandomVectors(oct);
    }

    public void Noise()
    {



        oct = oct < 5 ? 5 : oct;


        for (int i = 0; i < picture.Width; i += oct)
            for (int j = 0; j < picture.Height; j += oct)
            {
                DPoint topLeftVec = vectors[i / (oct + 1), j / (oct + 1)];
                DPoint topRightVec = vectors[i / (oct + 1) + 1, j / (oct + 1)];
                DPoint bottomLeftVec = vectors[i / (oct + 1), j / (oct + 1) + 1];
                DPoint bottomRightVec = vectors[i / (oct + 1) + 1, j / (oct + 1) + 1];


                for (int k = 0; k < oct; k++)
                {
                    if (i + k >= picture.Width)
                        continue;
                    for (int l = 0; l < oct; l++)
                    {
                        if (j + l >= picture.Height)
                            continue;

                        double paramX = k / (double)oct;
                        double paramY = l / (double)oct;

                        //Mod(paramX);
                        //Mod(paramX);
                        DPoint fromTopLeftVec = new DPoint(paramX, -paramY);
                        DPoint fromTopRightVec = new DPoint(-paramX, -(1 - paramY));
                        DPoint fromBottomLeftVec = new DPoint(1 - paramX, paramY);
                        DPoint fromBottomRightVec = new DPoint(-(1 - paramX), 1 - paramY);


                        // paramX = k / (double)oct;
                        //paramY = l / (double)oct;
                        double dotTopLeftVec = Dot(topLeftVec, fromTopLeftVec) / Math.Sqrt(2);
                        double dotTopRightVec = Dot(topRightVec, fromTopRightVec) / Math.Sqrt(2);
                        double dotBottomLeftVec = Dot(bottomLeftVec, fromBottomLeftVec) / Math.Sqrt(2);
                        double dotBottomRightVec = Dot(bottomRightVec, fromBottomRightVec) / Math.Sqrt(2);
                        double interTop = LInterp(dotTopLeftVec, dotTopRightVec, paramX);
                        double interBot = LInterp(dotBottomLeftVec, dotBottomRightVec, paramX);
                        double interY = LInterp(interTop, interBot, paramY);

                        if (interY > 0)
                        {

                            picture.SetPixel(i + k, j + l, Color.FromArgb((int)(picture.GetPixel(i + k, j + l).R + (255 - picture.GetPixel(i + k, j + l).R) * interY),
                                                                          (int)(picture.GetPixel(i + k, j + l).G + (255 - picture.GetPixel(i + k, j + l).G) * interY),
                                                                          (int)(picture.GetPixel(i + k, j + l).B + (255 - picture.GetPixel(i + k, j + l).B) * interY)));
                        }
                        else
                        {

                            picture.SetPixel(i + k, j + l, Color.FromArgb((int)(picture.GetPixel(i + k, j + l).R - Math.Abs((0 - picture.GetPixel(i + k, j + l).R) * Math.Abs(interY))),
                                                                          (int)(picture.GetPixel(i + k, j + l).G - Math.Abs((0 - picture.GetPixel(i + k, j + l).G) * Math.Abs(interY))),
                                                                          (int)(picture.GetPixel(i + k, j + l).B - Math.Abs((0 - picture.GetPixel(i + k, j + l).B) * Math.Abs(interY)))));
                        }
                        // разница между значением пикселя до его максимума(если коэф больше 0) или минимума(если коэф меньше нуля) умноженное на коэфициент 

                    }
                }


            }

    }
    public void SavePicture()
    {
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
        double RandomValue1;
        double RandomValue2;

        RandomValue1 = rand.NextDouble();
        RandomValue2 = RandomValue1 * (2 * Math.PI);

        res.X = Math.Cos(RandomValue2);
        res.Y = Math.Sin(RandomValue2);



        return res;

    }
    double LInterp(double left, double right, double param)
        => left + param * (right - left);
    double Mod(double t)
         //=> (1 - Math.Cos(t * Math.PI)) / 2;
         => t * t * t * (t * (t * 6 - 15) + 10);
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
}