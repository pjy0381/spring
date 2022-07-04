package com.example.firstProject.repository;

import com.example.firstProject.entity.Article;
import com.example.firstProject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;


    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        //Case 1: 4번
        {
           Long articleId = 4L;

           List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = new Article(4L, "당신의 인생 영화는?", "댓글ㄱㄱ");
           Comment a = new Comment(1L,article, "Park","굳 윌 헌팅");
           Comment b = new Comment(2L,article, "Kim","아이 엠 샘");
           Comment c = new Comment(3L,article, "Choi","쇼생크 탈출");
           List<Comment> expected = Arrays.asList(a,b,c);

           assertEquals(expected.toString(), comments.toString(), "4번글의 모든 댓글을 확인");
        }

        //Case 2: 1번
        {
            Long articleId = 1L;

            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = new Article(1L, "가가가가", "1111");
            List<Comment> expected = Arrays.asList();

            assertEquals(expected.toString(), comments.toString(), "1번글의 모든 댓글을 확인");
        }

        //Case 3: 9번
        {
            Long articleId = 9L;

            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = null;
            List<Comment> expected = Arrays.asList();

            assertEquals(expected, comments, "9번글의 모든 댓글을 확인");
        }

        //Case 4: 999번
        {
            Long articleId = 999L;

            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = null;
            List<Comment> expected = Arrays.asList();

            assertEquals(expected, comments, "999번글의 모든 댓글을 확인");
        }

        //Case 5: -1번
        {
            Long articleId = -1L;

            List<Comment> comments = commentRepository.findByArticleId(articleId);

            Article article = null;
            List<Comment> expected = Arrays.asList();

            assertEquals(expected, comments, "999번글의 모든 댓글을 확인");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        //Case 1: Park
        {
            String nickname = "Park";

            List<Comment> comments = commentRepository.findByNickname(nickname);

            Comment a = new Comment(1L,new Article(4L, "당신의 인생 영화는?", "댓글ㄱㄱ"), nickname,"굳 윌 헌팅");
            Comment b = new Comment(4L,new Article(5L, "당신의 소울 푸드는?", "댓글ㄱㄱ"), nickname,"치킨");
            Comment c = new Comment(7L,new Article(6L, "당신의 취미는?", "댓글ㄱㄱ"), nickname,"조깅");
            List<Comment> expected = Arrays.asList(a,b,c);

            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 확인");
        }

        //Case 2: Kim
        {
            String nickname = "Kim";

            List<Comment> comments = commentRepository.findByNickname(nickname);

            Comment a = new Comment(2L,new Article(4L, "당신의 인생 영화는?", "댓글ㄱㄱ"), nickname,"아이 엠 샘");
            Comment b = new Comment(5L,new Article(5L, "당신의 소울 푸드는?", "댓글ㄱㄱ"), nickname,"샤브샤브");
            Comment c = new Comment(8L,new Article(6L, "당신의 취미는?", "댓글ㄱㄱ"), nickname,"유투브");
            List<Comment> expected = Arrays.asList(a,b,c);

            assertEquals(expected.toString(), comments.toString(), "Kim의 모든 댓글을 확인");
        }

        //Case 3: null
        {
            String nickname = null;

            List<Comment> comments = commentRepository.findByNickname(nickname);

            List<Comment> expected = Arrays.asList();

            assertEquals(expected.toString(), comments.toString(), "null");
        }

        //Case 4: %%
        {
            String nickname = "%%";

            List<Comment> comments = commentRepository.findByNickname(nickname);

            Comment a = new Comment(1L,new Article(4L, "당신의 인생 영화는?", "댓글ㄱㄱ"), "Park","굳 윌 헌팅");
            Comment b = new Comment(2L,new Article(4L, "당신의 인생 영화는?", "댓글ㄱㄱ"), "Kim","아이 엠 샘");
            Comment c = new Comment(3L,new Article(4L, "당신의 인생 영화는?", "댓글ㄱㄱ"), "Choi","쇼생크 탈출");
            Comment d = new Comment(4L,new Article(5L, "당신의 소울 푸드는?", "댓글ㄱㄱ"), "Park","치킨");
            Comment e = new Comment(5L,new Article(5L, "당신의 소울 푸드는?", "댓글ㄱㄱ"), "Kim","샤브샤브");
            Comment f = new Comment(6L,new Article(5L, "당신의 소울 푸드는?", "댓글ㄱㄱ"), "Choi","초밥");
            Comment g = new Comment(7L,new Article(6L, "당신의 취미는?", "댓글ㄱㄱ"), "Park","조깅");
            Comment h = new Comment(8L,new Article(6L, "당신의 취미는?", "댓글ㄱㄱ"), "Kim","유투브");
            Comment i = new Comment(9L,new Article(6L, "당신의 취미는?", "댓글ㄱㄱ"), "Choi","독서");
            List<Comment> expected = Arrays.asList(a,b,c,d,e,f,g,h,i);

            assertEquals(expected.toString(), comments.toString(), "모든 댓글을 확인");
        }

        //Case 5: i
        {
            String nickname = "%i%";

            List<Comment> comments = commentRepository.findByNickname(nickname);

            Comment a = new Comment(2L,new Article(4L, "당신의 인생 영화는?", "댓글ㄱㄱ"), "Kim","아이 엠 샘");
            Comment b = new Comment(3L,new Article(4L, "당신의 인생 영화는?", "댓글ㄱㄱ"), "Choi","쇼생크 탈출");
            Comment c = new Comment(5L,new Article(5L, "당신의 소울 푸드는?", "댓글ㄱㄱ"), "Kim","샤브샤브");
            Comment d = new Comment(6L,new Article(5L, "당신의 소울 푸드는?", "댓글ㄱㄱ"), "Choi","초밥");
            Comment e = new Comment(8L,new Article(6L, "당신의 취미는?", "댓글ㄱㄱ"), "Kim","유투브");
            Comment f = new Comment(9L,new Article(6L, "당신의 취미는?", "댓글ㄱㄱ"), "Choi","독서");
            List<Comment> expected = Arrays.asList(a,b,c,d,e,f);

            assertEquals(expected.toString(), comments.toString(), "i가 포함된 모든 댓글을 확인");
        }
    }
}