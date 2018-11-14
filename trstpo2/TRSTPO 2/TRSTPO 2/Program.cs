using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace TRSTPO_2
{
    class Bridge
    {

        static void Main(string[] args)
        {

            int i = 10;

            Car cars = new Car(i);
            cars.Start();
            Console.ReadKey();
        }
    }
    class BridgeHandler
    {
        public Direction stat;
        public BridgeHandler()
        {
            stat = Direction.free;
        }
        public void SwitchDirection(Direction direction)
        {

            stat = direction;
            Console.WriteLine("Мост статус " + direction.ToString() + ":");

        }


    }
    class Car
    {
        BridgeHandler bridge = new BridgeHandler();
        Random random = new Random();
        object locker = new object();
        Thread[] cars;
        int count = 0;
        public Car(int i)
        {
            cars = new Thread[i];
            for (int j = 0; j < i; j++)
            {
                cars[j] = new Thread(Move);
                Direction dir = (Direction)(random.Next() % 2);
                cars[j].Name = dir.ToString();
            }

        }
        public void Start()
        {
            foreach (Thread c in cars)
            {
                c.Start();
            }
        }

        public void Move()
        {
            Direction dir = ToDirection(Thread.CurrentThread.Name);
            bool i = false;
            while (true)
            {
                lock (locker)
                {
                    if (dir == bridge.stat ||
                        bridge.stat == Direction.free)
                        i = true;
                }
                if (i)
                {
                    lock (locker)
                    {
                        count++;
                        bridge.SwitchDirection(dir);
                    }
                    Console.WriteLine("Машина по направлению " + dir.ToString() + " Заехала на мост");
                    Thread.Sleep(2000);

                    lock (locker)
                    {
                        Console.WriteLine("Машина по направлению " + dir.ToString() + " Съехала с моста");
                        count--;
                        if (count == 0)
                            bridge.SwitchDirection(Direction.free);
                    }
                    return;
                }

            }
        }
        public Direction ToDirection(string s)
        {

            if (s == "direct")
                return Direction.direct;
            if (s == "indirect")
                return Direction.indirect;
            if (s == "free")
                return Direction.free;

            Exception e = new Exception();
            throw e;

        }
    }

    public enum Direction
    {
        direct = 0,
        indirect = 1,
        free = 3

    }
}

