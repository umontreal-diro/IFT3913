"""exemple avec la méthode de bissection"""
import scipy

def bissection(function,x_1,x_2,precision,error):
    """la méthode de bissection"""
    x_3 = (x_1 + x_2) / 2
    while abs(x_1 - x_2) >= precision and abs(function(x_3)) >= error:
        x_3 = (x_1 + x_2) / 2
        if function(x_1) * function(x_3) <= 0:
            x_2 = x_3
        else:
            x_1 = x_3
    return x_3

def func_to_compute(var_x):
    """la function dont il faut trouver un zéro"""
    return (((var_x+1) * var_x - 3)) * var_x - 3

print(bissection(func_to_compute, 1.5, 2, 0.0005, 0.00001))
