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
Другими словами мы можеv отрисовать контент на underlay и на overlay

![](decor_concept.jpe4g)

Описание

```kotlin
val decorator = decorator { 
            
        }
```

```kotlin
val decorator = decorator {
            
            underlay { 
                
            }
            
            overlay { 
                
            }
            
            offsets { 
                
            }
        }
```


```kotlin
val decorator = decorator {

            underlay {
                layer { 
                    
                }
            }

            overlay {
                layer { 
                    
                }
            }

            offsets {
                offset { 
                    
                }
            }
        }
```

```kotlin
val decorator = decorator {

            underlay {
                layer { 
                    attachTo(...)
                    drawBy(...)
                }
            }

            overlay {
                layer {
                    attachTo(...)
                    drawBy(...)
                }
            }

            offsets {
                offset {
                    attachTo(...)
                    drawBy(...)
                }
            }
        }
```
