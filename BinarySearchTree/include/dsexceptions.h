#ifndef DS_EXCEPTIONS_H
#define DS_EXCEPTIONS_H

class UnderflowException { };
class IllegalArgumentException { };
class ArrayIndexOutOfBoundsException { };
class IteratorOutOfBoundsException { };
class IteratorMismatchException { };
class IteratorUninitializedException { };

#endif

class dsexceptions
{
    public:
        dsexceptions();
        virtual ~dsexceptions();
    protected:
    private:
};

#endif // DSEXCEPTIONS_H
