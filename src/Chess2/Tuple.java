package Chess2;

/**
 * Created by bchu on 10/1/16.
 */
public class Tuple<T1,T2>
{
    public T1 type1;
    public T2 type2;

    public Tuple(T1 t1, T2 t2)
    {
        type1 = t1;
        type2 = t2;
    }

    public T1 getType1()
    {
        return type1;
    }

    public T2 getType2()
    {
        return type2;
    }
}
