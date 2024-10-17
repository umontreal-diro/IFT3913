"""exemple avec la méthode de bissection"""

def bissection(func,pointUn,pointDeux,p,e):
    """la méthode de bissection"""
    pointTrois = (pointUn + pointDeux) / 2
    comptIt = 0
    while abs(pointUn - pointDeux) >= p and abs(func(pointTrois)) >= e:
        pointTrois = (pointUn + pointDeux) / 2
        comptIt += 1
        if func(pointUn) * func(pointTrois) <= 0:
            pointDeux = pointTrois
        else:
            pointUn = pointTrois
        print("Current iteration : " + str(comptIt))
    return pointTrois

def funcToCompute(x):
    """la function dont il faut trouver un zéro"""
    return (((x+1) * x - 3)) * x - 3

print(bissection(funcToCompute, 1.5, 2, 0.0005, 0.00001))
