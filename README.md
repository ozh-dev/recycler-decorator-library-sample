# Dls-decorator
Write RecyclerVIew ItemDecorator in DSL style.

Главная цель этой библиотеки упростить написание и сделать более наглядным создание ItemDecoration для RecyclerView.

RecyclerView.ItemDecoration имеет три метода для деокрирования View внтури RecyclerView \n
    
onDraw() - вызывается до отрисовки View внутри RecyclerView  

onDrawOver() - вызывается после отрисовки View внутри RecyclerView 

getItemOffsets() - используется для выставления отступов у View

Главный посыл

Грубо говоря с помощью ItemDecoration мы можем отрисовать что на Канвасе
ЗА вьюшками и ПЕРЕД ними. 
Другими словами мы можешь отрисовать контент на underlay и на overlay

![](decor_concept.jpeg)