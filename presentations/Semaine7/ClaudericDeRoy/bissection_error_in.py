"""exemple avec la méthode de bissection"""

def bissection(function,a,b,precision,error):
    """la méthode de bissection"""
    c = (a + b) / 2
    it = 0
    while abs(a - b) >= precision and abs(function(c)) >= error:
        c = (a + b) / 2
        it += 1
        if function(a) * function(c) <= 0:
            b = c
        else:
            a = c
        print("Current iteration : " + str(it))
    return c

def func_to_compute(x):
    """la function dont il faut trouver un zéro"""
    return (((x+1) * x - 3)) * x - 3

print(bissection(func_to_compute, 1.5, 2, 0.0005, 0.00001))
