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
            Console.ReadKey();
            noise.Noise();
        }
    }

    class PerlinNoise
    {
        Bitmap picture;
        public string Name { get; set; }

        public PerlinNoise(string name = "Picture1.bmp")
        {
            try
            {
                picture = new Bitmap(name);
                Name = name;
            }
            catch
            {
                Console.WriteLine("Name of picture is wrong. loaded default picture");
                Name = "Picture1.bmp";
                picture = new Bitmap(Name);
            }
            finally
            {
                Console.WriteLine($"Was loaded {Name}");
            }
        }

        public void Noise(int oct = 5)
        {
            oct = oct < 5 ? 5 : oct;
            for (int i = oct; i < picture.Width; i += oct)
                for (int j = oct; j < picture.Height; j += oct)
                {
                    for (int k = 0; k < oct; k++)
                    {
                        var px = picture.GetPixel(j, i);
                        picture.SetPixel(j+k, i+k, Color.FromArgb(GetInt(px)));
                    }
                }
            picture.Save("NewPicture.bmp");
        }

        int GetInt(Color color){
            return (int) color.R<<16| (int)color.G << 8 | (int) color.B;
        }
    }
}
