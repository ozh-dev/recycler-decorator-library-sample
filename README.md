[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](LICENSE)
[![](https://jitpack.io/v/OOOZH/DD.svg)](https://jitpack.io/#OOOZH/DD)

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

![](decor_concept.jpeg)

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
```kotlin

         layer {
                    drawBy(object : LayerDrawer() {
                        override fun draw(canvas: Canvas, view: View, recyclerView: RecyclerView, state: RecyclerView.State) {
                            //implement logic
                        }

                    })
                }

```

```kotlin
        offset {
                    drawBy(object : OffsetDrawer() {
                        override fun getItemOffsets(outRect: Rect, childView: View, recyclerView: RecyclerView, state: RecyclerView.State) {
                            //implement logic
                        }

                    })
                }
```
```kotlin
         underlay {
                layer {...}
                layer {...}
                layer {...}
                layer {...}
                    ...
            }

            overlay {
                layer {...}
                layer {...}
                layer {...}
                layer {...}
                ...
            }

            offsets {
                offset {...}
                offset {...}
                offset {...}
                offset {...}
                    ...
            }
```