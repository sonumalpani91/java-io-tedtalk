package com.tedtalk.service.impl;

import com.tedtalk.domain.vo.TedTalkVO;
import com.tedtalk.repository.impl.TedTalkRepositoryDefault;
import org.junit.Test;
import org.junit.Assert;

import java.time.LocalDate;
import java.util.Optional;

public class TedTalkManagerDefaultTest {
    private TedTalkRepositoryDefault tedTalkRepository = new TedTalkRepositoryDefault();

    @Test
    public void testGetTedTalk_success() {
        TedTalkManagerDefault tedTalkManager
                = new TedTalkManagerDefault(tedTalkRepository, null, null);

        TedTalkVO tedTalkVO = tedTalkManager.insertTedTalk(createTedTalk("title1", "author1", null, 200L, 200L, ""));
        Assert.assertNotNull(tedTalkVO.getId());

        Optional<TedTalkVO> tedTalkVO1 = tedTalkManager.getTedTalk(tedTalkVO.getId());
        Assert.assertTrue(tedTalkVO1.isPresent());

        Assert.assertEquals("title1", tedTalkVO1.get().getTitle());
        Assert.assertEquals("author1", tedTalkVO1.get().getAuthor());
        Assert.assertEquals(null, tedTalkVO1.get().getDate());
        Assert.assertEquals(200l, (long)tedTalkVO1.get().getLikes());
        Assert.assertEquals("", tedTalkVO1.get().getLink());
    }

    @Test
    public void testGetTedTalk_TedTalkNotPresent() {
        TedTalkManagerDefault tedTalkManager
                = new TedTalkManagerDefault(tedTalkRepository, null, null);

        Optional<TedTalkVO> tedTalkVO2 = tedTalkManager.getTedTalk(200);
        Assert.assertFalse(tedTalkVO2.isPresent());
    }

    private TedTalkVO createTedTalk(String title, String author, LocalDate date,
                                    Long views, Long likes, String links) {
        TedTalkVO tedTalkVO = new TedTalkVO();
        tedTalkVO.setTitle(title);
        tedTalkVO.setAuthor(author);
        tedTalkVO.setDate(date);
        tedTalkVO.setViews(views);
        tedTalkVO.setLikes(likes);
        tedTalkVO.setLink(links);
        return tedTalkVO;
    }
}
