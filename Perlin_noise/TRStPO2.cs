using System;
class Sheep
{
    public int x = 5;
    public int y = 5;
    public bool Chasing = false;
    public int Chase = 0;
    public Random rand = new Random();
    public void walking()
    {
        if ((x < 10) && (x > 0) && (y < 10) && (y > 0))
        {
            Chasing = false;
            Chase = 0;
        }

        if (Chase == 0)
        {


            switch (rand.Next(1, 9))
            {
                case 1:
                    x = x - 1;
                    y = y + 1;
                    break;
                case 2:
                    y = y + 1;
                    break;
                case 3:
                    x = x + 1;
                    y = y + 1;
                    break;
                case 4:
                    x = x + 1;
                    break;
                case 5:
                    x = x + 1;
                    y = y - 1;
                    break;
                case 6:
                    y = y - 1;
                    break;
                case 7:
                    x = x - 1;
                    y = y - 1;
                    break;
                case 8:
                    x = x - 1;
                    break;
            }
            Console.WriteLine($"{Thread.CurrentThread.Name} перешла в точку: ({x} ,{y})");
            Console.ReadKey();
        }
        if (Chase == 1)
        {
            x = 5;
            y = 5;
            Console.WriteLine("Овца вернулась в поле");
            Console.ReadKey();
        }

    }
    public void StartChase()
    {
        Chase = rand.Next(1, 10);
    }

}
class WatchDog
{
    // public int Chase = 0;
    //public Sheep target = null;
    //Random rand = new Random();
    public Sheep Sheep1;
    public Sheep Sheep2;
    public Shepherd Bob;
    public void Watch()
    {
        //if ((Sheep1.x < 10) && (Sheep1.x > 0) && (Sheep1.y < 10) && (Sheep1.y > 0) && (Sheep2.x < 10) && (Sheep2.x > 0) && (Sheep2.y < 10) && (Sheep2.y > 0))
        //{
        //    Chase = 0;
        //    target = null;

        //}
        if ((Sheep1.x > 10) || (Sheep1.x < 0) || (Sheep1.y > 10) || (Sheep1.y < 0) && (Sheep1.Chasing == false) && (Sheep2.Chasing == false))
        {
            Sheep1.Chasing = true;
            Sheep1.StartChase();
            Console.WriteLine("собака погналась за овцой");
            Console.ReadKey();

        }
        if ((Sheep2.x > 10) || (Sheep2.x < 0) || (Sheep2.y > 10) || (Sheep2.y < 0) && (Sheep2.Chasing == false) && (Sheep1.Chasing == false))
        {
            Sheep2.Chasing = true;
            Sheep2.StartChase();
            Console.WriteLine("собака погналась за овцой");
            Console.ReadKey();
        }

        if (((Sheep1.x > 10) || (Sheep1.x < 0) || (Sheep1.y > 10) || (Sheep1.y < 0)) && ((Sheep2.x > 10) || (Sheep2.x < 0) || (Sheep2.y > 10) || (Sheep2.y < 0)) && (Sheep1.Chasing == true || Sheep2.Chasing == true))
        {
            Barking(Bob);
            Console.WriteLine("собака зовет Пастуха");
            Console.ReadKey();
        }



    }
    public void Barking(Shepherd Bob)
    {
        Bob.Sleep = false;
    }



}
class Shepherd
{
    public bool Sleep = true;
    // public int Chase = 0;
    // public Sheep target = null;
    public Random rand = new Random();
    public Sheep Sheep1;
    public Sheep Sheep2;
    public WatchDog Bobik;
    public void Act()
    {
        if ((Sleep == false) && (Sheep1.Chasing == true))
        {
            Sheep2.Chasing = true;
            Sheep2.StartChase();
            Console.WriteLine("Пастух загоняет овцу");
            Console.ReadKey();
        }
        if ((Sleep == false) && (Sheep2.Chasing == true))
        {
            Sheep1.Chasing = true;
            Sheep1.StartChase();
            Console.WriteLine("Пастух загоняет овцу");
            Console.ReadKey();
        }
        if ((Sheep1.x < 10) && (Sheep1.x > 0) && (Sheep1.y < 10) && (Sheep1.y > 0) && (Sheep2.x < 10) && (Sheep2.x > 0) && (Sheep2.y < 10) && (Sheep2.y > 0))
        {
            Sleep = true;
            // Chase = 0;
            // target = null;
        }
    }
}
//main
// Sheep sheep1 = new Sheep();
//Sheep sheep2 = new Sheep();
//sheep1.x = 100;
//            sheep1.y = 100;
//            sheep2.x = 9;
//            sheep2.y = 9;
//            WatchDog Bobik = new WatchDog();
//Shepherd Tom = new Shepherd();
//Bobik.Sheep1 = sheep1;
//            Bobik.Sheep2 = sheep2;
//            Bobik.Bob = Tom;
//            Tom.Sheep1 = sheep1;
//            Tom.Sheep2 = sheep2;
//            Tom.Bobik = Bobik;

//            for(; ; )
//            {
//                Thread thread1 = new Thread(sheep1.walking);
//Thread thread2 = new Thread(sheep2.walking);
//thread1.Name = "Овца1";
//                thread2.Name = "Овца2";
//                Thread thread3 = new Thread(Bobik.Watch);
//Thread thread4 = new Thread(Tom.Act);
//thread1.Start();
//                thread2.Start();
//                thread3.Start();
//                thread4.Start();
//                if (sheep1.Chase > 0)
//                {
//                    sheep1.Chase--;
//                }
//                if (sheep2.Chase > 0)
//                {
//                    sheep2.Chase--;
//                }
//                Thread.Sleep(500);

//            }
//        }