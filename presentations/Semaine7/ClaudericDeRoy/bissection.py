def bissection(f,a,b,precision,error):
    """la méthode de bissection"""
    c = (a + b) / 2
    while abs(a - b) >= precision and abs(f(c)) >= error:
        c = (a + b) / 2
        if f(a) * f(c) <= 0:
            b = c
        else:
            a = c
    return c

def func(x):
    """la function dont il faut trouver un zéro"""
    return (((x+1) * x - 3)) * x - 3

print(bissection(func, 1.5, 2, 0.0005, 0.00001))
