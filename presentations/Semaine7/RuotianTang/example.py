def add(a, b):
    result = a + b
    return result


def fonction_complexe(a, b, c):

    if a > b:
        if b > c:
            print("a > b > c")
        else:
            print("a > b <= c")
    else:
        if b < c:
            print("a <= b < c")
        else:
            print("a <= b >= c")
