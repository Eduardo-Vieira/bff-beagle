package com.example.bff.builder

import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyFlex
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.action.Condition
import br.com.zup.beagle.widget.action.Navigate
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.context.valueOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.PageView
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScreenBuilder
import br.com.zup.beagle.widget.pager.PageIndicator
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.Text

data class Onboard(
    val img: String,
    val Title: String,
    val text: String,
    val backgroundColor: String
)

val data = listOf(
    Onboard(
        "https://i.ibb.co/6tp0GSQ/Rectangle-1.png",
        "Sharing made Easy",
        "Share a gift to your loved ones this christmas with ease, just with one or two clicks",
        "#F06868"
    ),
    Onboard(
        "https://i.ibb.co/Mhh547p/Rectangle-2.png",
        "No hassle, no Stress",
        "Share a gift to your loved ones this christmas with ease, just with one or two clicks",
        "#8F42F0"
    ),
    Onboard(
        "https://i.ibb.co/FqY8rrP/Humaaans-Standing.png",
        "Delivery to your Doorstep",
        "Share a gift to your loved ones this christmas with ease, just with one or two clicks",
        "#F0AE2F"
    ),
)

class OnboardScreen : ScreenBuilder {

    override fun build(): Screen {
        return Screen(
            child = Container(
                context = ContextData(
                    id = "pageIndicator",
                    value = 0
                ),
                children = listOf(
                    PageView(
                        children = data.map {
                            Container(
                                children = listOf(
                                    Image(
                                        path = ImagePath.Remote(
                                            remoteUrl = it.img
                                        ), mode = ImageContentMode.FIT_CENTER
                                    ).applyFlex(flex = Flex(grow = 1.0)).applyStyle(
                                        style = Style(
                                            margin = EdgeValue(bottom = 16.unitReal())
                                        )
                                    ),
                                    Text(
                                        text = it.Title,
                                        alignment = TextAlignment.LEFT,
                                        textColor = "#FFFFFF",
                                        styleId = "DesignSystem.Text.H1"
                                    ).applyStyle(
                                        style = Style(
                                            margin = EdgeValue(bottom = 8.unitReal())
                                        )
                                    ),
                                    Text(
                                        text = it.text,
                                        alignment = TextAlignment.LEFT,
                                        textColor = "#FFFFFF"
                                    )
                                )
                            )
                                .applyFlex(Flex(grow = 1.0, justifyContent = JustifyContent.FLEX_END))
                                .applyStyle(
                                    style = Style(
                                        backgroundColor = it.backgroundColor,
                                        padding = EdgeValue(all = 10.unitReal(), bottom = 20.unitPercent())
                                    )
                                )
                        },
                        onPageChange = listOf(SetContext("pageIndicator", "@{onPageChange}")),
                        currentPage = expressionOf("@{pageIndicator}")
                    ),
                    Container(
                        children = listOf(
                            Container(
                                children = listOf(
                                    Button(
                                        text = expressionOf("@{condition(eq(pageIndicator, 2), '', 'Skip')}"),
                                        styleId = "DesignSystem.Button.link",
                                        onPress = listOf(Navigate.PopView())
                                    ),
                                    Container(
                                        children = listOf(
                                            PageIndicator(
                                                numberOfPages = 3,
                                                selectedColor = "#CCCCCC",
                                                unselectedColor = "#F4F4F4",
                                                currentPage = expressionOf("@{pageIndicator}")
                                            )
                                        )
                                    ).applyStyle(
                                        Style(
                                            size = Size(width = 33.0.unitPercent())
                                        )
                                    ),
                                    Button(
                                        text = expressionOf("@{condition(eq(pageIndicator, 2), 'Finish', 'Next')}"),
                                        styleId = "DesignSystem.Button.link",
                                        onPress = listOf(
                                            Condition(
                                                condition = expressionOf("@{lt(pageIndicator, 2)}"),
                                                onTrue = listOf(
                                                    SetContext(
                                                        "pageIndicator",
                                                        valueOf("@{sum(pageIndicator,1)}")
                                                    )
                                                ),
                                                onFalse = listOf(Navigate.PopView())
                                            )
                                        )
                                    )
                                )
                            ).applyStyle(
                                style = Style(
                                    flex = Flex(
                                        flexDirection = FlexDirection.ROW,
                                        justifyContent = JustifyContent.SPACE_BETWEEN
                                    )
                                )
                            )
                        )
                    ).applyStyle(
                        style = Style(
                            size = Size(width = 100.0.unitPercent()),
                            positionType = PositionType.ABSOLUTE,
                            flex = Flex(alignSelf = AlignSelf.CENTER)
                        )
                    )
                )
            ).applyFlex(Flex(grow = 1.0, justifyContent = JustifyContent.FLEX_END))
        )
    }
}