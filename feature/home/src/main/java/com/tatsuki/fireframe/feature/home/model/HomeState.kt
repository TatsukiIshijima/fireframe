package com.tatsuki.fireframe.feature.home.model

import com.tatsuki.fireframe.core.model.SlideGroup

data class HomeState(
    val sourceTypes: List<SourceType>,
    val slideshowGroups: List<SlideGroup>,
    val selectedSlideGroupId: Long,
    val deleteTargetSlideGroup: SlideGroup?,
    val isEnableSourceTypes: Boolean,
) {

    fun isSelectedAnySlideGroup(): Boolean {
        return selectedSlideGroupId != -1L
    }

    fun isSelectedSlideGroup(slideGroup: SlideGroup): Boolean {
        return selectedSlideGroupId == slideGroup.id
    }

    companion object {
        fun create(): HomeState {
            return HomeState(
                sourceTypes = emptyList(),
                slideshowGroups = emptyList(),
                selectedSlideGroupId = -1L,
                deleteTargetSlideGroup = null,
                isEnableSourceTypes = true,
            )
        }

        fun fake(
            sourceTypes: List<SourceType> = listOf<SourceType>(
                SourceType.LocalStorage(),
            ),
            slideGroups: List<SlideGroup> = listOf(
                SlideGroup.fake(
                    id = 1,
                    groupName = "SlideGroup1",
                ),
                SlideGroup.fake(
                    id = 2,
                    groupName = "SlideGroup2",
                ),
                SlideGroup.fake(
                    id = 3,
                    groupName = "SlideGroup3",
                ),
            ),
            selectedSlideGroupId: Long = 1,
            isEnableSourceTypes: Boolean = true,
        ): HomeState {
            return HomeState(
                sourceTypes = sourceTypes,
                slideshowGroups = slideGroups,
                selectedSlideGroupId = selectedSlideGroupId,
                deleteTargetSlideGroup = null,
                isEnableSourceTypes = isEnableSourceTypes,
            )
        }
    }
}
